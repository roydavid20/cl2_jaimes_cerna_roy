package test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.EntityManager;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import model.Cliente;
import model.Cuenta;
import model.Movimiento;
import model.Rol;
import model.TipoCuenta;

public class JpaTest {

	public static void main(String[] args) {
		

		EntityManagerFactory factory = Persistence.createEntityManagerFactory("default");
		EntityManager manager = factory.createEntityManager();
		EntityTransaction tx = manager.getTransaction();
		
		tx.begin();
		
		 // Crear y persistir roles
        Rol rolTitular = new Rol();
        rolTitular.setDescripcion("Titular");
        rolTitular.setActivo(true);

        Rol rolBeneficiario = new Rol();
        rolBeneficiario.setDescripcion("Beneficiario");
        rolBeneficiario.setActivo(true);

        manager.persist(rolTitular);
        manager.persist(rolBeneficiario);

        // Crear y persistir cliente
        Cliente cli = new Cliente();
        cli.setApellidoMaterno("Medina");
        cli.setApellidoPaterno("Rodriguez");
        cli.setNombres("Juan");
        cli.setCuentas(new ArrayList<>());
        cli.setRoles(new ArrayList<>());

        // Agregar roles al cliente
        cli.getRoles().add(rolTitular);
        cli.getRoles().add(rolBeneficiario);

        manager.persist(cli);

        // Crear y persistir cuentas, tipo de cuenta y movimientos
        Cuenta cuent = new Cuenta();
        cuent.setNumeroCuenta("123456789");
        cuent.setSaldo(5000d);
        cuent.setActiva(true);

        TipoCuenta tipoCuent = new TipoCuenta();
        tipoCuent.setDescripcion("Cuenta Ahorros");
        tipoCuent.setMoneda("USD");

        Movimiento movimiento = new Movimiento();
        movimiento.setDescripcion("Deposito Inicial");
        movimiento.setFecha(LocalDate.now());
        movimiento.setMonto(500d);

        // Establecer relaciones
        cuent.setTipoCuenta(tipoCuent);
        cuent.setCliente(cli);
        movimiento.setCuenta(cuent);

        // Persistir cuentas, tipo de cuenta y movimientos
        manager.persist(cuent);
        manager.persist(tipoCuent);
        manager.persist(movimiento);

        tx.commit();

        List<Cliente> lista = manager.createQuery("from Cliente", Cliente.class).getResultList();
        for (Cliente c : lista) {
            System.out.println(c);
        }
	    
	}

}
