import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';

import {AppRoutingModule} from './app-routing.module';
import {AppComponent} from './app.component';
import {UserComponent} from "./user/user.component";
import {BookComponent} from "./book/book.component";
import {BookListComponent} from "./book-list/book-list.component";
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {HttpClientModule} from "@angular/common/http";
import {MatTableModule} from "@angular/material";

@NgModule({
	declarations: [
		AppComponent,
		UserComponent,
		BookComponent,
		BookListComponent
	],
	imports: [
		BrowserModule,
		AppRoutingModule,
		FormsModule,
		HttpClientModule,
		ReactiveFormsModule,
		MatTableModule
	],
	providers: [],
	bootstrap: [AppComponent]
})
export class AppModule {
}
