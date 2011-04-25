//last updated by jonan 7 okt
//- tambah method baru getSql1() 

package com.bigjava.jsf;
/**
 * 95 Percentile Methode, 5%  top deleted,  data < 5 delete 1 data
 * @author yulius eka 13 oktober 2010
 *
 */
public class Persentile {
	String kode = "";
	int awal;
	String batas;
	public Persentile()
	{
		getSql();
	}
	
	public String getSql() // untuk data > 5
	{
		kode = "order by avg(bw_out),avg(bw_in) desc  limit 5,10000000000;";
		return kode;
	}
	
	public String getSql1() // untuk data < 5
	{
		kode = "order by avg(bw_out),avg(bw_in) desc  limit 1,10000000000;";
		return kode;
	}
	
	
}