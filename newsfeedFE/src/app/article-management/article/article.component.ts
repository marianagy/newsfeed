import {Component, OnInit} from '@angular/core';
import {ArticleService} from "../article.service";

@Component({
  selector: 'app-article',
  templateUrl: './article.component.html',
  styleUrls: ['./article.component.scss']
})
export class ArticleComponent implements OnInit {

  article;
  articleUpvoted;

  constructor(private articleServie: ArticleService) {
    this.article = {
      id: "",
      title: "",
      content: "",
      image: "",
      user: "",
      tags: "",
      categories: "",
      nrUpvotes: ""

    };
  }

  userHasUpvoted(articleId) {
    this.articleServie.userHasUpvoted(articleId)
      .subscribe(
        data => {
          this.articleUpvoted = data;

        },
        err => {
          console.log(err);
        }
      );
  }


  addUpvote(articleId) {
    if (this.articleUpvoted) {
      this.articleServie.removeUpvote(articleId).subscribe(
        data => {
          this.userHasUpvoted(articleId);
        },
        err => {
          console.log(err);
        }
      );
    } else {
      this.articleServie.addUpvote(articleId).subscribe(
        data => {
          this.userHasUpvoted(articleId);
        },
        err => {
          console.log(err);
        }
      );
    }
  }


  // todo: sa iau id-ul articolului pe care dau click (din url de exemplu)
  getArticle(id) {
    id = 1;
    this.articleServie.getArticleById(id).subscribe(
      data => {
        this.article = data;
        console.log(this.article);
      },
      err => {
        console.log(err);
      }
    )
  }

  ngOnInit() {
    this.getArticle(1);
    this.userHasUpvoted(1);
  }

  getUpvoteNrForArticle() {
  }

}
