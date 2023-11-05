package model;

import java.util.List;




import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinTable;

import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

import jakarta.persistence.JoinColumn;

@Entity
@Table(name = "tbl_cliente")

@Data
public class Cliente {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "apellido_materno")
    private String apellidoMaterno;

    @Column(name = "apellido_paterno")
    private String apellidoPaterno;

    @Column(name = "nombres")
    private String nombres;

    @OneToMany(mappedBy = "cliente")
    private List<Cuenta> cuentas;

    @ManyToMany
    @JoinTable(
        name = "cliente_rol",
        joinColumns = @JoinColumn(name = "cliente_id"),
        inverseJoinColumns = @JoinColumn(name = "rol_id")
    )
    private List<Rol> roles;

	@Override
	public String toString() {
		return "Cliente [id=" + id + ", apellidoMaterno=" + apellidoMaterno + ", apellidoPaterno=" + apellidoPaterno
				+ ", nombres=" + nombres + ", cuentas=" + cuentas + ", roles=" + roles + "]";
	}

	

	
	
    
	

}
