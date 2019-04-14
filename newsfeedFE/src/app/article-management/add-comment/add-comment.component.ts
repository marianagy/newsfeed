import {Component, OnInit} from '@angular/core';
import {ArticleService} from "../article.service";
import {ActivatedRoute} from "@angular/router";
import {AuthenticationService} from "../../user/authentication.service";
import {DatePipe} from "@angular/common";

@Component({
  selector: 'app-add-comment',
  templateUrl: './add-comment.component.html',
  styleUrls: ['./add-comment.component.scss'],
  providers: [DatePipe]
})
export class AddCommentComponent implements OnInit {


  commentList: any;
  articleId: any;
  comment: any;
  currentDate;


  constructor(private articleService: ArticleService,
              private authenticationService: AuthenticationService,
              private activatedRoute: ActivatedRoute
  ) {
    this.comment = {
      id: "",
      content: "",
      user: "",
      article: "",
      date: ""
    }
  }

  ngOnInit() {
    // get param form url
    let param: any = this.activatedRoute.snapshot.params;
    console.log(param.id);
    this.articleId = param.id;
    this.loadComments();
  }

  submitForm() {
    console.log("Submit data");

    this.comment.user = localStorage;
    this.comment.date = new Date();
    this.articleService.getArticleById(this.articleId)
      .subscribe(data => {
          this.comment.article = data;
          this.articleService.addComment(this.comment)
            .subscribe(data => {
                console.log("Succes: " + data);
                this.loadComments();
              }, err => {
                console.log(err)
              }
            )
        }, err => {
          console.log("Error happened.");
          console.log(err);
        }
      );


  }

  loadComments() {
    this.articleService.getCommentsForArticle(this.articleId)
      .subscribe(
        data => {
          console.log("Everything ok.");
          console.log(data);
          this.commentList = data;
        },
        err => {
          console.log("Error happened.");
          console.log(err);
        }
      )
  }

  editCommentDialog() {
  }

  deleteComment() {
  }

  verifyUser() {
  }
}
