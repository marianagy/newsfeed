import {Component, OnInit} from '@angular/core';
import {ArticleService} from "../article.service";

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
  styleUrls: ['./article-list.component.scss']
})
export class ArticleListComponent implements OnInit {

  articleData: ArticleData;

  constructor(private articleServie: ArticleService) {
  }

  articleList: any;
  articleUpvotes: any;

  ngOnInit() {
    this.loadArticles();
  }

  innerUpvoteCheck(articleID) {
    console.log(this.articleUpvotes);
    if (this.articleUpvotes != undefined) {
      if (this.articleUpvotes[articleID] != undefined && this.articleUpvotes[articleID] != null) {

        return this.articleUpvotes[articleID];
      }
    }
    return false;
  }

  userHasUpvoted(articleId) {
    this.articleServie.userHasUpvoted(articleId)
      .subscribe(
        data => {
          this.articleUpvotes[articleId] = data;

        },
        err => {
          console.log(err);
        }
      );
  }

  loadArticles() {

    this.articleServie.getAllArticles()
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

        }, error => {
          console.log("Error happened.");
          console.log(error);
        }
      )
  }

}
