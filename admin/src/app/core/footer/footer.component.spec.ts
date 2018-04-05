import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { FooterComponent } from './footer.component';
import { DateTimeProviderService } from '../../shared/services/date-time-provider.service';
import { SharedModule } from '../../shared/shared.module';

class Page {
  // getter properties wait to query the DOM until called.
  get copyright()     { return this.query<HTMLSpanElement>('.copyright'); }

  constructor(private fixture: ComponentFixture<FooterComponent>) { }

  //// query helpers ////
  private query<T>(selector: string): T {
    return this.fixture.nativeElement.querySelector(selector);
  }
}

describe('FooterComponent', () => {
  let component: FooterComponent;
  const dateTimeProviderServiceStub: Partial<DateTimeProviderService> = {
    currentYear(): number {
      return 0;
    }
  };
  let fixture: ComponentFixture<FooterComponent>;
  let page: Page;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      imports: [SharedModule],
      declarations: [ FooterComponent ],
      providers: [
        {
          provide: DateTimeProviderService,
          useValue: dateTimeProviderServiceStub
        }
      ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(FooterComponent);
    component = fixture.componentInstance;
    page = new Page(fixture);
  });

  it('should show only current year value', () => {
    const dtpService = TestBed.get(DateTimeProviderService);
    spyOn(dtpService, 'currentYear').and.returnValue(2018);
    fixture.detectChanges();
    expect(page.copyright.textContent).toBe('© Vít Herin 2018');
  });

  it('should show both year values', () => {
    const dtpService = TestBed.get(DateTimeProviderService);
    spyOn(dtpService, 'currentYear').and.returnValue(2019);
    fixture.detectChanges();
    expect(page.copyright.textContent).toBe('© Vít Herain 2018 - 2019');
  });
});
