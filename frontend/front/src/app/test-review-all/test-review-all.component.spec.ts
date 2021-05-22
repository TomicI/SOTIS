import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TestReviewAllComponent } from './test-review-all.component';

describe('TestReviewAllComponent', () => {
  let component: TestReviewAllComponent;
  let fixture: ComponentFixture<TestReviewAllComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ TestReviewAllComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(TestReviewAllComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
