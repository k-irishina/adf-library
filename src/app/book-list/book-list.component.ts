import {Component, OnInit} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {take} from "rxjs/operators";
import {environment} from "../../environments/environment";

@Component({
  selector: 'app-book-list',
  templateUrl: './book-list.component.html',
  styleUrls: ['./book-list.component.scss'],
})
export class BookListComponent implements OnInit {
	public displayedColumns: string[] = ['bookId', 'bookName', 'isbn', 'year', 'currentReader'];
	public dataSource: any;

	constructor(private http: HttpClient) {}

	public ngOnInit(): void {
		this.http.get(`${environment.baseUrl}/books/all`).pipe(take(1)).subscribe(data => {
			this.dataSource = data;
		})
	}

}
