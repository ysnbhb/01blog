import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DeletePostPopup } from './delete-post-popup';

describe('DeletePostPopup', () => {
  let component: DeletePostPopup;
  let fixture: ComponentFixture<DeletePostPopup>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [DeletePostPopup]
    })
    .compileComponents();

    fixture = TestBed.createComponent(DeletePostPopup);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
