import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { MantenimientoTablasMaestrasService } from 'app/entities/mantenimiento-tablas-maestras/mantenimiento-tablas-maestras.service';
import { IMantenimientoTablasMaestras, MantenimientoTablasMaestras } from 'app/shared/model/mantenimiento-tablas-maestras.model';

describe('Service Tests', () => {
  describe('MantenimientoTablasMaestras Service', () => {
    let injector: TestBed;
    let service: MantenimientoTablasMaestrasService;
    let httpMock: HttpTestingController;
    let elemDefault: IMantenimientoTablasMaestras;
    let expectedResult: IMantenimientoTablasMaestras | IMantenimientoTablasMaestras[] | boolean | null;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
      });
      expectedResult = null;
      injector = getTestBed();
      service = injector.get(MantenimientoTablasMaestrasService);
      httpMock = injector.get(HttpTestingController);

      elemDefault = new MantenimientoTablasMaestras(0, 'AAAAAAA');
    });

    describe('Service methods', () => {
      it('should find an element', () => {
        const returnedFromService = Object.assign({}, elemDefault);

        service.find(123).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(elemDefault);
      });

      it('should create a MantenimientoTablasMaestras', () => {
        const returnedFromService = Object.assign(
          {
            id: 0,
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.create(new MantenimientoTablasMaestras()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a MantenimientoTablasMaestras', () => {
        const returnedFromService = Object.assign(
          {
            tecnicaEvaluativa: 'BBBBBB',
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.update(expected).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should return a list of MantenimientoTablasMaestras', () => {
        const returnedFromService = Object.assign(
          {
            tecnicaEvaluativa: 'BBBBBB',
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.query().subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush([returnedFromService]);
        httpMock.verify();
        expect(expectedResult).toContainEqual(expected);
      });

      it('should delete a MantenimientoTablasMaestras', () => {
        service.delete(123).subscribe(resp => (expectedResult = resp.ok));

        const req = httpMock.expectOne({ method: 'DELETE' });
        req.flush({ status: 200 });
        expect(expectedResult);
      });
    });

    afterEach(() => {
      httpMock.verify();
    });
  });
});
