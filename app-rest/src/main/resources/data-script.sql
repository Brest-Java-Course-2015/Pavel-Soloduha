INSERT INTO DETAIL (detailid, detailname)
    VALUES (3, 'Шуруп');
INSERT INTO DETAIL (detailid, detailname)
    VALUES (12, 'Молоток');
INSERT INTO DETAIL (detailid, detailname)
    VALUES (16, 'Гвоздь');
INSERT INTO DETAIL (detailid, detailname)
    VALUES (25, 'Лопата');

INSERT INTO DOCHEAD (documentid, documenttype, documentdata, documentprice)
    VALUES (40, 1, '10.10.2015', 5000);
INSERT INTO DOCHEAD (documentid, documenttype, documentdata, documentprice)
    VALUES (43, 1, '12.10.2015', 10300);
INSERT INTO DOCHEAD (documentid, documenttype, documentdata, documentprice)
    VALUES (50, 2, '14.10.2015', 7400);

INSERT INTO DOCBODY (documentid, detailid, detailcount)
    VALUES (40, 3, 50);
INSERT INTO DOCBODY (documentid, detailid, detailcount)
    VALUES (40, 16, 20);
INSERT INTO DOCBODY (documentid, detailid, detailcount)
    VALUES (40, 25, 1);
INSERT INTO DOCBODY (documentid, detailid, detailcount)
    VALUES (43, 3, 100);
INSERT INTO DOCBODY (documentid, detailid, detailcount)
    VALUES (43, 25, 2);
INSERT INTO DOCBODY (documentid, detailid, detailcount)
    VALUES (43, 16, 50);
INSERT INTO DOCBODY (documentid, detailid, detailcount)
    VALUES (43, 12, 5);
INSERT INTO DOCBODY (documentid, detailid, detailcount)
    VALUES (50, 25, 1);
INSERT INTO DOCBODY (documentid, detailid, detailcount)
    VALUES (50, 16, 70);
INSERT INTO DOCBODY (documentid, detailid, detailcount)
    VALUES (50, 12, 2);