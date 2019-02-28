import {Component, OnInit} from '@angular/core';
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {HttpClient} from "@angular/common/http";
import {take} from "rxjs/operators";
import {environment} from "../../environments/environment";

@Component({
  selector: 'app-book',
  templateUrl: './book.component.html',
  styleUrls: ['./book.component.scss'],
})
export class BookComponent implements OnInit {
	public formGroup: FormGroup;
	public success: boolean = false;

	constructor(private http: HttpClient) {}

	public ngOnInit(): void {
		this.formGroup = new FormGroup({
			bookId: new FormControl({value: null, disabled: true}),
			bookName: new FormControl(null, Validators.required),
			isbn: new FormControl(null, Validators.required),
			year: new FormControl(null, [Validators.pattern(/^[0-9]*$/), Validators.required])
		})
	}

	public onSubmit(): void {
		const data: any = {
			...this.formGroup.value,
			year: this.formGroup.value.year + ''
		};
		console.log(data);
		this.http.post(`${environment.baseUrl}/books/add`, this.formGroup.value)
			.pipe(take(1))
			.subscribe(res => {
				this.formGroup.patchValue({...res});
				this.formGroup.disable();
				this.success = true;
			});
	}
}
