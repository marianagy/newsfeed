import {Component, Inject, OnInit} from '@angular/core';
import {MAT_DIALOG_DATA, MatDialogRef} from "@angular/material";
import {ArticleService} from "../article.service";
import {AddCommentComponent} from "../add-comment/add-comment.component";


@Component({
  selector: 'app-edit-comment',
  templateUrl: './edit-comment.component.html',
  styleUrls: ['./edit-comment.component.scss']
})
export class EditCommentComponent implements OnInit {

  constructor(public dialogRef: MatDialogRef<AddCommentComponent>,
              private articleService: ArticleService,
              @Inject(MAT_DIALOG_DATA) public data) {
  }

  ngOnInit() {

  }

  onNoClick(): void {
    console.log("Data" + this.data);
    this.dialogRef.close(this.data);
  }

  submitForm() {
    console.log("Submit data");
    console.log(this.data);

    this.articleService.editComment(this.data).subscribe(
      data => {
        this.dialogRef.close(this.data);
      }, err => {
        console.log(err);
      }
    )
  }

}
