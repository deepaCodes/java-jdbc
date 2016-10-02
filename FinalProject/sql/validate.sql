
SELECT data_type,column_name from information_schema.columns WHERE table_name='sales'

-----------

cust,prod,1_sum_quant,2_sum_quant,3_sum_quant
3
cust,prod
1_sum_quant,1_avg_quant,2_sum_quant,3_sum_quant,3_avg_quant,1_max_quant
state=NY
state=NJ
state=CT
1_sum_quant>2*2_sum_quant or 1_avg_quant>3_avg_quant



-----------------

with ny_data as (
	select cust,prod,sum(quant) as sum_ny, avg(quant) ny_avg
	from sales where state='NY' 
	group by cust,prod
),
nj_data as (
	select cust,prod,sum(quant) as sum_nj,avg(quant) nj_avg
	from sales where state='NJ' 
	group by cust,prod
),
ct_data as (
	select cust,prod,sum(quant) as sum_ct,avg(quant) ct_avg
	from sales where state='CT' 
	group by cust,prod
)
select distinct s.cust,s.prod,ny.sum_ny,nj.sum_nj,ct.sum_ct,ny.ny_avg,ct.ct_avg
from sales s
left outer join ny_data ny on ny.cust=s.cust and ny.prod=s.prod
left outer join nj_data nj on nj.cust=s.cust and nj.prod=s.prod
left outer join ct_data ct on ct.cust=s.cust and ct.prod=s.prod
where ny.sum_ny > 2 * nj.sum_nj or ny.ny_avg > ct.ct_avg
