import {Component, OnInit} from '@angular/core';
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {HttpClient} from "@angular/common/http";
import {take} from "rxjs/operators";
import {environment} from "../../environments/environment";

@Component({
	selector: 'app-user',
	templateUrl: './user.component.html',
	styleUrls: ['./user.component.scss'],
})
export class UserComponent implements OnInit {
	public formGroup: FormGroup;
	public success: boolean = false;

	constructor(private http: HttpClient) {}

	public ngOnInit(): void {
		this.formGroup = new FormGroup({
			readerId: new FormControl({value: null, disabled: true}),
			firstName: new FormControl(null, Validators.required),
			lastName: new FormControl(null, Validators.required),
			phoneNumber: new FormControl(null, Validators.required)
		})
	}

	public onSubmit(): void {
		const data: any = {
			...this.formGroup.value,
			phoneNumber: this.formGroup.value.phoneNumber + ''
		};
		console.log(data);
		this.http.post(`${environment.baseUrl}/readers/register`, this.formGroup.value)
			.pipe(take(1))
			.subscribe(res => {
				this.formGroup.patchValue({...res});
				this.formGroup.disable();
				this.success = true;
			});
	}
}
