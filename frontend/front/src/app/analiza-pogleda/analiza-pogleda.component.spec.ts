import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AnalizaPogledaComponent } from './analiza-pogleda.component';

describe('AnalizaPogledaComponent', () => {
  let component: AnalizaPogledaComponent;
  let fixture: ComponentFixture<AnalizaPogledaComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AnalizaPogledaComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AnalizaPogledaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
