import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { Requerimiento14TestModule } from '../../../test.module';
import { MantenimientoTablasMaestrasDetailComponent } from 'app/entities/mantenimiento-tablas-maestras/mantenimiento-tablas-maestras-detail.component';
import { MantenimientoTablasMaestras } from 'app/shared/model/mantenimiento-tablas-maestras.model';

describe('Component Tests', () => {
  describe('MantenimientoTablasMaestras Management Detail Component', () => {
    let comp: MantenimientoTablasMaestrasDetailComponent;
    let fixture: ComponentFixture<MantenimientoTablasMaestrasDetailComponent>;
    const route = ({ data: of({ mantenimientoTablasMaestras: new MantenimientoTablasMaestras(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [Requerimiento14TestModule],
        declarations: [MantenimientoTablasMaestrasDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(MantenimientoTablasMaestrasDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(MantenimientoTablasMaestrasDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load mantenimientoTablasMaestras on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.mantenimientoTablasMaestras).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
