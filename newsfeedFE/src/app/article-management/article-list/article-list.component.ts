import {Component, Input, OnInit, ViewChild} from '@angular/core';
import {ArticleService} from "../article.service";
import {MatDialog, MatPaginator, PageEvent} from "@angular/material";
import {EditArticleComponent} from "../edit-article/edit-article.component";
import {ActivatedRoute} from "@angular/router";
import {TranslateService} from "@ngx-translate/core";

export interface ArticleData {
  id: any;
  title: string;
  content: string;
  image: any;
  user: any;
  tags: any;
  categories: any;
  nrUpvotes: any;
}

@Component({
  selector: 'app-article-list',
  templateUrl: './article-list.component.html',
  styleUrls: ['./article-list.component.scss'],

})
export class ArticleListComponent implements OnInit {
  // MatPaginator Inputs
  pageSize = 5;

  private _dynamicdata: string;

  get dynamicdata(): string {
    return this._dynamicdata;
  }

  @Input()
  set dynamicdata(name: string) {
    //this._dynamicdata = (name && name.trim()) || '<no name set>';
    console.log("I am in load articles by categ");
    this._dynamicdata = name;
    this.doCategs(name);

  }

  // daca sunt articolele unui user sau sau toate articolele
  @Input() articleFilter: string;

  username: String;
  // articleList: any;
  articleList: any[];
  articleUpvotes: any;
  article: any;
  articles: any;
  new_article: any;

  doCategs(name) {
    this.articleServie.getCategoryArticles(name, this.pageIndex, this.pageSize)
      .subscribe(
        data => {
          console.log(data);
          this.articleList = (Object.values(data['articleDTOList']));
          this.length = data['amount'];

        }, err => {
          console.log("Error happened.");
          console.log(err);
        }
      )
  }
  length = 100;
  pageIndex = 0;
  pageSizeOptions: number[] = [5, 10, 25, 100];
  pageEvent: PageEvent;
  @ViewChild(MatPaginator) paginator: MatPaginator;
  private sub: any;

  constructor(private articleServie: ArticleService,
              public dialog: MatDialog,
              private route: ActivatedRoute,
              private translate: TranslateService) {

  }

  setPageSizeOptions(setPageSizeOptionsInput: string) {
    this.pageSizeOptions = setPageSizeOptionsInput.split(',').map(str => +str);
  }


  // loadData(pageIndex, pageSize){
  //
  //   this.articles.forEach(function (entity) {
  //
  //   });
  //   console.log("Article list "+this.articleList);
  // }

  ngOnInit() {
    this.loadArticles();
    console.log("Article filter " + this.articleFilter);
    //this.loadData(0,this.pageSize)
    // this.filter(this.pageIndex, this.pageSize, this.article.id);
    // this.sub = this.route.params.subscribe(params => {
    //   this.article.id = +params['id'];
    // });
    // if (this.article.id) {
    //   this.filter(0, 1, this.article.id);
    // }
  }

  innerUpvoteCheck(articleId) {
    console.log(this.articleUpvotes);
    if (this.articleUpvotes != undefined) {
      if (this.articleUpvotes[articleId] != undefined && this.articleUpvotes[articleId] != null) {

        return this.articleUpvotes[articleId];
      }
    }
    return false;
  }

  userHasUpvoted(articleId) {
    this.articleServie.userHasUpvoted(articleId)
      .subscribe(
        // data = true or false
        data => {
          this.articleUpvotes[articleId] = data;

        },
        err => {
          console.log(err);
        }
      );
  }

  verifyUser(articleUserUsername: String) {
    this.username = localStorage.getItem("username");
    return this.username === articleUserUsername;
  }

  onPageChange(event) {
    console.log(event);
    console.log(this.pageIndex);
    this.pageIndex = event.pageIndex;
    this.pageSize = event.pageSize;
    console.log(this._dynamicdata)

    if (this._dynamicdata !== '' && this._dynamicdata !== undefined) {
      this.doCategs(this._dynamicdata);
    } else {

      this.loadArticles();

    }
  }

  loadArticles() {
    console.log("I am in load articles!!!!!!!!!");
    console.log("Article filter: ");
    console.log(this.articleFilter);

    this.articleServie.getFilteredArticles(this.articleFilter, this.pageIndex, this.pageSize)
      .subscribe(
        data => {
          console.log(data);
          this.articleList = (Object.values(data['articleDTOList']));
          this.length = data['amount'];


        }, err => {
          console.log("Error happened.");
          console.log(err);
        }
      )

  }

  editArticleDialog(articleId) {
    this.articleServie.getArticleById(articleId).subscribe(
      data => {
        this.article = data;
        console.log(this.article);
        const dialogRef = this.dialog.open(EditArticleComponent, {
          width: '1200px',
          height: '90vh',
          data: {
            "id": this.article.id,
            "title": this.article.title,
            "content": this.article.content,
            "image": this.article.image,
            "user": this.article.user,
            "tagList": this.article.tagList,
            "categoryList": this.article.categoryList,
            "nrUpvotes": this.article.nrUpvotes,
            "profileDTO": this.article.profileDTO,
          }
        });
        dialogRef.afterClosed().subscribe(result => {
            console.log('The dialog was closed');
            console.log(result);
            this.new_article = result;
            this.article.title = this.new_article.title;
            this.article.content = this.new_article.content;
            console.log(this.article.content);
            this.article.image = this.new_article.image;
            this.loadArticles();
          },
          err => {
            console.log(err);
          }
        );
      });
  }

  // Shorten a string to less than maxLen characters without truncating words.
  shorten(str, maxLen, separator = ' ') {
    if (str.length <= maxLen) return str;
    return str.substr(0, str.lastIndexOf(separator, maxLen));
  }

  deleteArticle(articleId) {
    this.articleServie.deleteArticle(articleId).subscribe(
      data => {
        console.log(data);
        this.loadArticles();
      },
      err => {
        console.log(err);
      }
    );

  }

  // filter(pageIndex = 0, pageSize = this.pageSize, id: number) {
  //   this.pageIndex = pageIndex;
  //   this.filter(pageIndex, pageSize, id).subscribe(
  //     {
  //       next: (value: any[]) => {
  //         console.log(value);
  //         this.articleList = new MatTableDataSource<ArticleData[]>(value['filteredList']);
  //         this.length = value['actualListSize'];
  //         //this.sortDataSource();
  //       }
  //     }
  //   );
  // }


}
