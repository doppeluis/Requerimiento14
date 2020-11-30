import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { Requerimiento14TestModule } from '../../../test.module';
import { MantenimientoTablasMaestrasUpdateComponent } from 'app/entities/mantenimiento-tablas-maestras/mantenimiento-tablas-maestras-update.component';
import { MantenimientoTablasMaestrasService } from 'app/entities/mantenimiento-tablas-maestras/mantenimiento-tablas-maestras.service';
import { MantenimientoTablasMaestras } from 'app/shared/model/mantenimiento-tablas-maestras.model';

describe('Component Tests', () => {
  describe('MantenimientoTablasMaestras Management Update Component', () => {
    let comp: MantenimientoTablasMaestrasUpdateComponent;
    let fixture: ComponentFixture<MantenimientoTablasMaestrasUpdateComponent>;
    let service: MantenimientoTablasMaestrasService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [Requerimiento14TestModule],
        declarations: [MantenimientoTablasMaestrasUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(MantenimientoTablasMaestrasUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(MantenimientoTablasMaestrasUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(MantenimientoTablasMaestrasService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new MantenimientoTablasMaestras(123);
        spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.update).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));

      it('Should call create service on save for new entity', fakeAsync(() => {
        // GIVEN
        const entity = new MantenimientoTablasMaestras();
        spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.create).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));
    });
  });
});
