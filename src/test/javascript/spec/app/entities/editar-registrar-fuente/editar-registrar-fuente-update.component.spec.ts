import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { Requerimiento14TestModule } from '../../../test.module';
import { EditarRegistrarFuenteUpdateComponent } from 'app/entities/editar-registrar-fuente/editar-registrar-fuente-update.component';
import { EditarRegistrarFuenteService } from 'app/entities/editar-registrar-fuente/editar-registrar-fuente.service';
import { EditarRegistrarFuente } from 'app/shared/model/editar-registrar-fuente.model';

describe('Component Tests', () => {
  describe('EditarRegistrarFuente Management Update Component', () => {
    let comp: EditarRegistrarFuenteUpdateComponent;
    let fixture: ComponentFixture<EditarRegistrarFuenteUpdateComponent>;
    let service: EditarRegistrarFuenteService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [Requerimiento14TestModule],
        declarations: [EditarRegistrarFuenteUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(EditarRegistrarFuenteUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(EditarRegistrarFuenteUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(EditarRegistrarFuenteService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new EditarRegistrarFuente(123);
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
        const entity = new EditarRegistrarFuente();
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
