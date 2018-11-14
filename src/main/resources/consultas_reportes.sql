/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Author:  Mario Ruiz
 * Created: 29/10/2018
 */


-- consulta de comparendos mas realizados.
--De la tabla comparendos_partes tomar codigo de tipo comparendo y repeticiones, 
--inner con tipos_comparendos para sacar el nombre.

select  t2.tipo as "tipo de infraccion", count(t1.tipo_comparendo_codigo) as "repeticiones"
from comparendos_partes t1
inner join tipo_comparendos  t2 on t1.tipo_comparendo_codigo = t2.codigo
GROUP BY t2.tipo HAVING COUNT(t1.tipo_comparendo_codigo)
ORDER BY COUNT(t1.tipo_comparendo_codigo) DESC;


--consulta de infractores reincidentes
--consultar de la tabla infractores y partes 

select t2.identificacion as "identificación", t2.nombre as "nombre", t2.apellidos as "apellidos", count(t1.infractor_codigo) as "cantidad de partes"
from partes t1
inner join infractores t2 on t1.infractor_codigo = t2.codigo
group by t2.identificacion  having count(t1.infractor_codigo)
order by count(t1.infractor_codigo) desc;


--consulta de partes del dia
--obtener partes de una fecha

select t1.codigo as "codigo parte", t1.fecha as "fecha parte", t4.identificacion as "identificacion", 
t4.nombre as "nombre", t4.apellidos as "apellidos",  t3.tipo as "comparendo"
from partes t1
inner join infractores t4 on t1.infractor_codigo = t4.codigo
inner join comparendos_partes t2 on t1.codigo = t2.parte_codigo
inner join tipo_comparendos t3 on t2.tipo_comparendo_codigo = t3.codigo
where t1.fecha = '2018-10-30'
order by t1.codigo asc;
