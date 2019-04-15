import {Component, Input, OnInit} from '@angular/core';
import {ArticleService} from "../article.service";
import {MatDialog} from "@angular/material";
import {EditArticleComponent} from "../edit-article/edit-article.component";

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

  @Input() articleFilter: string;

  username: String;
  articleList: any;
  articleUpvotes: any;
  article: any;
  new_article: any;

  constructor(private articleServie: ArticleService,
              public dialog: MatDialog) {

  }


  ngOnInit() {
    this.loadArticles();
    console.log(this.articleFilter);
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

  loadArticles() {

    this.articleServie.getAllArticles(this.articleFilter)
      .subscribe(
        data => {
          console.log("Everything ok.");
          console.log(data);
          // this.articleData.id = data[0]["id"];
          // this.articleData.title = data[0]["title"];
          // this.articleData.content = data[0]["content"];
          // this.articleData.user = data[0]["user"];
          // this.articleData.tags = data[0]["tags"];
          // this.articleData.categories = data[0]["categories"];
          // this.articleData.nrUpvotes = data[0]["nrUpvotes"];

          this.articleList = data;


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
          width: '350px',
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
            this.article.image = this.new_article.image;
            this.loadArticles();
          },
          err => {
            console.log(err);
          }
        );
      });
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


}
