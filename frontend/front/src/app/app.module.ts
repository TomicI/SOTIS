import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LoginComponent } from './login/login.component';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';

import { BsDropdownModule } from 'ngx-bootstrap/dropdown';
import { TooltipModule } from 'ngx-bootstrap/tooltip';
import { ModalModule } from 'ngx-bootstrap/modal';
import { AddQuestionFormComponent } from './add-question-form/add-question-form.component';
import { QuestionPreviewComponent } from './question-preview/question-preview.component';
import { ProfileComponent } from './profile/profile.component';
import { NavigationComponent } from './navigation/navigation.component';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { CreateTestComponent } from './create-test/create-test.component';
import { TestPreviewComponent } from './test-preview/test-preview.component';
import { TestComponent } from './test/test.component';
import { TestReviewAllComponent } from './test-review-all/test-review-all.component';
import { TestReviewComponent } from './test-review/test-review.component';

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    AddQuestionFormComponent,
    QuestionPreviewComponent,
    ProfileComponent,
    NavigationComponent,
    CreateTestComponent,
    TestPreviewComponent,
    TestComponent,
    TestReviewAllComponent,
    TestReviewComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    ReactiveFormsModule,
    HttpClientModule,
    BrowserAnimationsModule,
    BsDropdownModule.forRoot(),
    TooltipModule.forRoot(),
    ModalModule.forRoot(),
    NgbModule,
    FormsModule
  ],
  providers: [],
  bootstrap: [AppComponent],
  exports: [BsDropdownModule, TooltipModule, ModalModule]
})
export class AppModule { }
