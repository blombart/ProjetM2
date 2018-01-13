package fr.lsmbo.msda.recover.filters;

import fr.lsmbo.msda.recover.lists.IonReporters;
import fr.lsmbo.msda.recover.model.IonReporter;
import fr.lsmbo.msda.recover.model.Spectrum;

/**
 * Filter to keep specific spectrum according to specific ion. For a specific
 * m/z with a tolerance, check if the spectrum have this ion, in this case the
 * value for recover will be true.
 * 
 * @author BL
 *
 */
public class IonReporterFilter implements BasicFilter {

	private String name;
	private Float moz;
	private Float tolerance;


	public void setParameters(String _name, Float _moz, Float _tolerance) {
		name = _name;
		moz = _moz;
		tolerance = _tolerance;
	}

	@Override
	public Boolean isValid(Spectrum spectrum) {
		float mozMin = moz - tolerance;
		float mozMax = moz + tolerance;
		boolean ionReporterFound = false;

		for (int i = 0; i < spectrum.getNbFragments(); i++) {
			float mozFragment = spectrum.getFragments().get(i).getMz();

			if (mozFragment > mozMin && mozFragment < mozMax) {
				ionReporterFound = true;
				break;
			}
		}

		if (ionReporterFound)
			return true;
		return false;
	}

	@Override
	public String getFullDescription() {
		String allIons = "";
		for (IonReporter ir : IonReporters.getIonReporters()) {
			allIons += "###" + ir.toString() + "\n";
		}

		return "###Ion Reporter Filter used with : " + IonReporters.getIonReporters().size() + " ion(s) reporter." + "\n" + allIons;
	}

}