import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { EditarRegistrarFuenteService } from 'app/entities/editar-registrar-fuente/editar-registrar-fuente.service';
import { IEditarRegistrarFuente, EditarRegistrarFuente } from 'app/shared/model/editar-registrar-fuente.model';

describe('Service Tests', () => {
  describe('EditarRegistrarFuente Service', () => {
    let injector: TestBed;
    let service: EditarRegistrarFuenteService;
    let httpMock: HttpTestingController;
    let elemDefault: IEditarRegistrarFuente;
    let expectedResult: IEditarRegistrarFuente | IEditarRegistrarFuente[] | boolean | null;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
      });
      expectedResult = null;
      injector = getTestBed();
      service = injector.get(EditarRegistrarFuenteService);
      httpMock = injector.get(HttpTestingController);

      elemDefault = new EditarRegistrarFuente(0, 0, 'AAAAAAA', 'AAAAAAA');
    });

    describe('Service methods', () => {
      it('should find an element', () => {
        const returnedFromService = Object.assign({}, elemDefault);

        service.find(123).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(elemDefault);
      });

      it('should create a EditarRegistrarFuente', () => {
        const returnedFromService = Object.assign(
          {
            id: 0,
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.create(new EditarRegistrarFuente()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a EditarRegistrarFuente', () => {
        const returnedFromService = Object.assign(
          {
            codigo: 1,
            descripcion: 'BBBBBB',
            estado: 'BBBBBB',
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.update(expected).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should return a list of EditarRegistrarFuente', () => {
        const returnedFromService = Object.assign(
          {
            codigo: 1,
            descripcion: 'BBBBBB',
            estado: 'BBBBBB',
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

      it('should delete a EditarRegistrarFuente', () => {
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
