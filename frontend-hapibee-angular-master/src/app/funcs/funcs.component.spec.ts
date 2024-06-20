import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { FuncsComponent } from './funcs.component';

describe('ExamplesComponent', () => {
  let component: FuncsComponent;
  let fixture: ComponentFixture<FuncsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ FuncsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(FuncsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
