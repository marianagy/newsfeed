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

  categories;

  checkedValue(event) {
    console.log("Checked: " + event.checked);
    console.log("Data: ");
    console.log(event);

    if (this.data.categoryList === undefined) {
      this.data.categoryList = [];
    }
    if (event.checked) {
      this.data.categoryList.push(event.source.name);
    } else {
      var index = this.data.categoryList.indexOf(event.source.name);
      if (index !== -1) this.data.categoryList.splice(index, 1);
    }
    console.log(this.data.categoryList);
  }

  ngOnInit() {
    this.articleService.getAllCategories().subscribe(
      data => {
        console.log(data);
        this.categories = data;
      }, err => {
        console.log(err);
      }
    );

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
    console.log("Data: " + this.data);

    this.articleService.addArticle(this.data).subscribe(
      data => {
        this.dialogRef.close(this.data);
      }, err => {
        console.log(err);
      }
    )
  }

}
