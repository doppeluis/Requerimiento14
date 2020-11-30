import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { Requerimiento14TestModule } from '../../../test.module';
import { EditarRegistrarFuenteDetailComponent } from 'app/entities/editar-registrar-fuente/editar-registrar-fuente-detail.component';
import { EditarRegistrarFuente } from 'app/shared/model/editar-registrar-fuente.model';

describe('Component Tests', () => {
  describe('EditarRegistrarFuente Management Detail Component', () => {
    let comp: EditarRegistrarFuenteDetailComponent;
    let fixture: ComponentFixture<EditarRegistrarFuenteDetailComponent>;
    const route = ({ data: of({ editarRegistrarFuente: new EditarRegistrarFuente(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [Requerimiento14TestModule],
        declarations: [EditarRegistrarFuenteDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(EditarRegistrarFuenteDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(EditarRegistrarFuenteDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load editarRegistrarFuente on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.editarRegistrarFuente).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
