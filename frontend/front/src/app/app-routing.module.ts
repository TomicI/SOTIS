import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { LoginComponent } from './login/login.component';
import { AddQuestionFormComponent } from "./add-question-form/add-question-form.component";
import { QuestionPreviewComponent } from "./question-preview/question-preview.component";

const routes: Routes =
[
  {
      path: 'sign_in',
      component: LoginComponent
  },
  {
      path: 'addQuestion',
      component: AddQuestionFormComponent
  },
  {
    path: 'setRegionsInQuestion',
    component: QuestionPreviewComponent
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
