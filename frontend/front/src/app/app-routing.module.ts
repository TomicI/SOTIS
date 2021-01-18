import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { LoginComponent } from './login/login.component';
import { AddQuestionFormComponent } from "./add-question-form/add-question-form.component";

const routes: Routes =
[
  {
      path: 'sign_in',
      component: LoginComponent
  },
  {
      path: 'addQuestion',
      component: AddQuestionFormComponent
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
