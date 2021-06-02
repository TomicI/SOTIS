import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { LoginComponent } from './login/login.component';
import { AddQuestionFormComponent } from "./add-question-form/add-question-form.component";
import { QuestionPreviewComponent } from "./question-preview/question-preview.component";
import {ProfileComponent} from "./profile/profile.component";
import {CreateTestComponent} from "./create-test/create-test.component";
import {TestPreviewComponent} from "./test-preview/test-preview.component";
import {TestComponent} from "./test/test.component";
import {TestReviewAllComponent} from "./test-review-all/test-review-all.component";
import {TestReviewComponent} from "./test-review/test-review.component";
import {AnalizaPogledaComponent} from "./analiza-pogleda/analiza-pogleda.component";

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
  },
  {
    path: 'profile',
    component: ProfileComponent
  },
  {
    path: 'create_test',
    component: CreateTestComponent
  },
  {
    path: 'test_preview',
    component: TestPreviewComponent
  },
  {
    path: 'test/:id',
    component: TestComponent
  },
  {
    path: 'tests_review',
    component: TestReviewAllComponent
  },
  {
    path: 'test_review',
    component: TestReviewComponent
  },
  {
    path: 'analiza',
    component: AnalizaPogledaComponent
  }

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
