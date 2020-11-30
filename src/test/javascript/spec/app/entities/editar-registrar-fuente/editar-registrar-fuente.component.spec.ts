import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { ActivatedRoute, convertToParamMap } from '@angular/router';

import { Requerimiento14TestModule } from '../../../test.module';
import { EditarRegistrarFuenteComponent } from 'app/entities/editar-registrar-fuente/editar-registrar-fuente.component';
import { EditarRegistrarFuenteService } from 'app/entities/editar-registrar-fuente/editar-registrar-fuente.service';
import { EditarRegistrarFuente } from 'app/shared/model/editar-registrar-fuente.model';

describe('Component Tests', () => {
  describe('EditarRegistrarFuente Management Component', () => {
    let comp: EditarRegistrarFuenteComponent;
    let fixture: ComponentFixture<EditarRegistrarFuenteComponent>;
    let service: EditarRegistrarFuenteService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [Requerimiento14TestModule],
        declarations: [EditarRegistrarFuenteComponent],
        providers: [
          {
            provide: ActivatedRoute,
            useValue: {
              data: of({
                defaultSort: 'id,asc',
              }),
              queryParamMap: of(
                convertToParamMap({
                  page: '1',
                  size: '1',
                  sort: 'id,desc',
                })
              ),
            },
          },
        ],
      })
        .overrideTemplate(EditarRegistrarFuenteComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(EditarRegistrarFuenteComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(EditarRegistrarFuenteService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new EditarRegistrarFuente(123)],
            headers,
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.editarRegistrarFuentes && comp.editarRegistrarFuentes[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });

    it('should load a page', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new EditarRegistrarFuente(123)],
            headers,
          })
        )
      );

      // WHEN
      comp.loadPage(1);

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.editarRegistrarFuentes && comp.editarRegistrarFuentes[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });

    it('should calculate the sort attribute for an id', () => {
      // WHEN
      comp.ngOnInit();
      const result = comp.sort();

      // THEN
      expect(result).toEqual(['id,desc']);
    });

    it('should calculate the sort attribute for a non-id attribute', () => {
      // INIT
      comp.ngOnInit();

      // GIVEN
      comp.predicate = 'name';

      // WHEN
      const result = comp.sort();

      // THEN
      expect(result).toEqual(['name,desc', 'id']);
    });
  });
});
