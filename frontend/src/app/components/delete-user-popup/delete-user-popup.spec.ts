import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DeleteUserPopup } from './delete-user-popup';

describe('DeleteUserPopup', () => {
  let component: DeleteUserPopup;
  let fixture: ComponentFixture<DeleteUserPopup>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [DeleteUserPopup]
    })
    .compileComponents();

    fixture = TestBed.createComponent(DeleteUserPopup);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
