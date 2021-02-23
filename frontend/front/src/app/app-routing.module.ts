import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { LoginComponent } from './login/login.component';
import { AddQuestionFormComponent } from "./add-question-form/add-question-form.component";
import { QuestionPreviewComponent } from "./question-preview/question-preview.component";
import {ProfileComponent} from "./profile/profile.component";
import {CreateTestComponent} from "./create-test/create-test.component";
import {TestPreviewComponent} from "./test-preview/test-preview.component";

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
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
