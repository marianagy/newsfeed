import {Component, Inject, OnInit} from '@angular/core';
import {MAT_DIALOG_DATA, MatDialogRef} from "@angular/material";
import {ProfileComponent} from "../../user/profile/profile.component";
import {ArticleService} from "../article.service";


@Component({
  selector: 'app-add-article',
  templateUrl: './add-article.component.html',
  styleUrls: ['./add-article.component.scss']
})
export class AddArticleComponent implements OnInit {

  constructor(public dialogRef: MatDialogRef<ProfileComponent>,
              private articleService: ArticleService,
              @Inject(MAT_DIALOG_DATA) public data) {
  }

  ngOnInit() {
  }

  onNoClick(): void {
    this.dialogRef.close(this.data);
  }

  previewImage(event) {
    let reader = new FileReader();

    if (event.target.files && event.target.files.length) {
      const [file] = event.target.files;
      reader.onload = () => {
        var img = new Image();
        img.width = 100;
        // @ts-ignore
        img.src = reader.result;
        document.getElementById("chosen_image").appendChild(img);
        this.data.image = reader.result;
      };
      reader.readAsDataURL(file);

    }

  }

  submitForm() {
    console.log("Submit data");
    console.log(this.data);

    this.articleService.addArticle(this.data).subscribe(
      data => {
        this.dialogRef.close(this.data);
      }, err => {
        console.log(err);
      }
    )
  }

}
