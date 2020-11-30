import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { ActivatedRoute, convertToParamMap } from '@angular/router';

import { Requerimiento14TestModule } from '../../../test.module';
import { MantenimientoTablasMaestrasComponent } from 'app/entities/mantenimiento-tablas-maestras/mantenimiento-tablas-maestras.component';
import { MantenimientoTablasMaestrasService } from 'app/entities/mantenimiento-tablas-maestras/mantenimiento-tablas-maestras.service';
import { MantenimientoTablasMaestras } from 'app/shared/model/mantenimiento-tablas-maestras.model';

describe('Component Tests', () => {
  describe('MantenimientoTablasMaestras Management Component', () => {
    let comp: MantenimientoTablasMaestrasComponent;
    let fixture: ComponentFixture<MantenimientoTablasMaestrasComponent>;
    let service: MantenimientoTablasMaestrasService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [Requerimiento14TestModule],
        declarations: [MantenimientoTablasMaestrasComponent],
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
        .overrideTemplate(MantenimientoTablasMaestrasComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(MantenimientoTablasMaestrasComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(MantenimientoTablasMaestrasService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new MantenimientoTablasMaestras(123)],
            headers,
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.mantenimientoTablasMaestras && comp.mantenimientoTablasMaestras[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });

    it('should load a page', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new MantenimientoTablasMaestras(123)],
            headers,
          })
        )
      );

      // WHEN
      comp.loadPage(1);

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.mantenimientoTablasMaestras && comp.mantenimientoTablasMaestras[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });

    it('should re-initialize the page', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new MantenimientoTablasMaestras(123)],
            headers,
          })
        )
      );

      // WHEN
      comp.loadPage(1);
      comp.reset();

      // THEN
      expect(comp.page).toEqual(0);
      expect(service.query).toHaveBeenCalledTimes(2);
      expect(comp.mantenimientoTablasMaestras && comp.mantenimientoTablasMaestras[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });

    it('should calculate the sort attribute for an id', () => {
      // WHEN
      comp.ngOnInit();
      const result = comp.sort();

      // THEN
      expect(result).toEqual(['id,asc']);
    });

    it('should calculate the sort attribute for a non-id attribute', () => {
      // INIT
      comp.ngOnInit();

      // GIVEN
      comp.predicate = 'name';

      // WHEN
      const result = comp.sort();

      // THEN
      expect(result).toEqual(['name,asc', 'id']);
    });
  });
});
