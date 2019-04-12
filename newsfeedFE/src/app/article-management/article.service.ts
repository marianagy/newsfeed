import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";

@Injectable({
  providedIn: 'root'
})
export class ArticleService {

  baseURL = 'http://localhost:8080/api';

  constructor(private http: HttpClient) {
  }


  getAllArticles(articleFilter) {
    if (articleFilter === undefined) {

      return this.http.get(this.baseURL + '/articles');
    } else {
      return this.http.get(this.baseURL + '/get-user-articles')
    }
  }

  addUpvote(articleId: any) {
    let body = new URLSearchParams();
    body.set('article_id', articleId);
    console.log(body);
    return this.http.post(this.baseURL + '/save-upvote',
      body.toString(),

      {
        headers: new HttpHeaders(
          {'Content-Type': 'application/x-www-form-urlencoded'}
        )
      });
  }

  addArticle(article) {
    return this.http.post(this.baseURL + '/save-article',
      article,
      {
        headers: new HttpHeaders(
          {'Content-Type': 'application/json'}
        )
      });

  }

  editArticle(article) {
    return this.http.put(this.baseURL + '/update-article',
      article,
      {
        headers: new HttpHeaders(
          {'Content-Type': 'application/json'}
        )
      });
  }

  deleteArticle(articleId) {

    return this.http.post(this.baseURL + '/delete-article/' + articleId,

      {
        headers: new HttpHeaders(
          {'Content-Type': 'application/x-www-form-urlencoded'}
        )
      });
  }

  userHasUpvoted(articleId: any) {

    return this.http.get(this.baseURL + '/user-upvoted/' + articleId);
  }

  getArticleById(id) {

    return this.http.get(this.baseURL + '/articles/' + id);
  }

  removeUpvote(articleId) {
    let body = new URLSearchParams();
    body.set('article_id', articleId);
    console.log(body);
    return this.http.post(this.baseURL + '/remove-upvote',
      body.toString(),
      {
        headers: new HttpHeaders(
          {'Content-Type': 'application/x-www-form-urlencoded'}
        )
      });
  }

}


