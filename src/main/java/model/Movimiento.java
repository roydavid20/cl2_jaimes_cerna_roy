package model;


import java.util.Date;
import java.time.LocalDate;
import java.time.ZoneId;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
@Entity
@Data
@Table(name = "tbl_name")
public class Movimiento {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	@Column(name = "descripcion")
    private String descripcion;
	@Column(name = "fecha")
    private Date fecha;
	@Column(name = "monto")
    private double monto;

    @ManyToOne
    @JoinColumn(name = "cuenta_id")
    private Cuenta cuenta;

	public void setFecha(LocalDate now) {
		LocalDate localDate = LocalDate.now();
        Date date = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
        this.fecha = date;
		
	}

}
