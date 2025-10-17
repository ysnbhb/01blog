import { ComponentFixture, TestBed } from '@angular/core/testing';

import { LoginNav } from './login-nav';

describe('LoginNav', () => {
  let component: LoginNav;
  let fixture: ComponentFixture<LoginNav>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [LoginNav]
    })
    .compileComponents();

    fixture = TestBed.createComponent(LoginNav);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
