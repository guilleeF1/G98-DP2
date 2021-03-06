package acme.entities.spamword;

import java.util.List;

import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;

import acme.entities.threshold.Threshold;
import acme.framework.entities.DomainEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Spamword extends DomainEntity {
	
	protected static final long serialVersionUID = 1L;
	

	@NotBlank
	protected String word;
	

	
	public static Boolean isSpam(final String texto, final List<Spamword> spamwords, final Threshold threshold) {
		Integer n = 0;
		for (final Spamword s : spamwords) {
			if (!s.getWord().replaceAll("\\s","").isEmpty()) {
				n += s.getWord().replaceAll("\\s","").length() * Spamword.numberOfTimesContained(texto.replaceAll("\\s","").toLowerCase(), s.getWord().replaceAll("\\s","").toLowerCase());
			}
		}

		return n != 0 && threshold.getUmbral() >= texto.replaceAll("\\s","").length() / n;
	}
	
	private static Integer numberOfTimesContained(final String text, final String s) {
		int repetitions = 0;
		final int n = s.length();
		int m = 0;
		char charToEvaluate = s.toCharArray()[0];
		for (final char c : text.toCharArray()) {
			if (c == charToEvaluate) {
				m += 1;
				if (m == n) {
					repetitions += 1;
					charToEvaluate = s.toCharArray()[0];
					m = 0;
				}
				else {
					charToEvaluate = s.toCharArray()[m];
				}
			}
			else {
				charToEvaluate = s.toCharArray()[0];
				m = 0;
			}
		}
		return repetitions;
	}

}