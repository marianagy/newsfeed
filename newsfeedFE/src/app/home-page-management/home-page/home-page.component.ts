import {Component, OnInit} from '@angular/core';
import {ArticleService} from "../../article-management/article.service";

@Component({
  selector: 'app-home-page',
  templateUrl: './home-page.component.html',
  styleUrls: ['./home-page.component.scss']
})
export class HomePageComponent implements OnInit {

  categories;
  category: string;

  constructor(private articleService: ArticleService) {
  }

  ngOnInit() {
    this.articleService.getAllCategories().subscribe(
      data => {
        console.log("I am good gboy");
        console.log(data);
        this.categories = data;
      }, err => {
        console.log(err);
      }
    );
  }

}
