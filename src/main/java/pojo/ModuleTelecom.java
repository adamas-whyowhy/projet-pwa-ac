package pojo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;


@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class ModuleTelecom {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private int numero;
	
	public abstract int envoyerMessage(String message);
}
