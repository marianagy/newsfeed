import {Component, OnInit} from '@angular/core';
import {ArticleService} from "../article.service";
import {ActivatedRoute} from "@angular/router";
import {AuthenticationService} from "../../user/authentication.service";
import {DatePipe} from "@angular/common";
import {MatDialog} from "@angular/material";
import {EditCommentComponent} from "../edit-comment/edit-comment.component";

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
  username;
  new_comment;


  constructor(private articleService: ArticleService,
              private authenticationService: AuthenticationService,
              private activatedRoute: ActivatedRoute,
              public dialog: MatDialog
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

  editCommentDialog(commentId) {
    this.articleService.getCommentById(commentId)
      .subscribe(
        data => {
          this.comment = data;
          console.log(this.comment);
          const dialogRef = this.dialog.open(EditCommentComponent, {
            width: '350px',
            data: {
              "id": this.comment.id,
              "content": this.comment.content,
              "user": this.comment.user,
              "article": this.comment.article,
              "date": this.comment.date
            }
          });
          dialogRef.afterClosed().subscribe(result => {
              console.log('The dialog was closed');
              console.log(result);
              this.new_comment = result;
              this.comment.content = this.new_comment.content;
              this.comment.date = new Date();
              this.loadComments();
            },
            err => {
              console.log(err);
            }
          );
        }
      )

  }

  deleteComment(commentId) {

    this.articleService.deleteComment(commentId)
      .subscribe(
        data => {
          console.log(data);
          this.loadComments();
        },
        err => {
          console.log(err);
        }
      );

  }

  verifyUser(username: String) {

    this.username = localStorage.getItem("username");
    return this.username === username;
  }
}
