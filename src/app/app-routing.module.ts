import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {UserComponent} from "./user/user.component";
import {BookComponent} from "./book/book.component";
import {BookListComponent} from "./book-list/book-list.component";

const appRoutes: Routes = [
  {path: '', pathMatch: 'full', redirectTo: '/user'},
  {path: 'user', component: UserComponent},
  {path: 'book', component: BookComponent},
  {path: 'book-list', component: BookListComponent},
  {path: '**', redirectTo: '/user'}
];

@NgModule({
  imports: [RouterModule.forRoot(appRoutes, {onSameUrlNavigation: 'reload'})],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
