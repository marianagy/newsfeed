import {Component, OnInit} from '@angular/core';
import {ArticleService} from "../article.service";
import {ActivatedRoute} from "@angular/router";
import {MatDialog} from "@angular/material";
import {TranslateService} from "@ngx-translate/core";

@Component({
  selector: 'app-article',
  templateUrl: './article.component.html',
  styleUrls: ['./article.component.scss']
})
export class ArticleComponent implements OnInit {

  article;
  articleUpvoted;

  constructor(private articleServie: ArticleService,
              private activatedRoute: ActivatedRoute,
              public dialog: MatDialog,
              private translate: TranslateService) {
    this.article = {
      id: "",
      title: "",
      content: "",
      image: "",
      user: "",
      tags: "",
      categories: "",
      nrUpvotes: "",
      profileDTO: ""

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


  getArticle(articleId) {

    this.articleServie.getArticleById(articleId).subscribe(
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
    // get param form url
    let param: any = this.activatedRoute.snapshot.params;
    console.log(param.id);
    this.getArticle(param.id);
    this.userHasUpvoted(param.id);


  }

  getUpvoteNrForArticle() {
  }

}
