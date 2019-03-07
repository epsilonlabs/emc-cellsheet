package org.eclipse.epsilon.emc.cellsheet.xcore;

import java.util.Collections;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.plugin.EcorePlugin;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.xcore.XPackage;
import org.eclipse.emf.ecore.xcore.XcorePackage;
import org.eclipse.emf.ecore.xcore.XcoreStandaloneSetup;
import org.eclipse.emf.ecore.xcore.generator.XcoreGenerator;
import org.eclipse.emf.mwe.utils.StandaloneSetup;
import org.eclipse.xtext.resource.XtextResource;
import org.eclipse.xtext.resource.XtextResourceSet;

import com.google.inject.Injector;

public class Xcore2Ecore {

	public static void main(String[] args) throws Exception {
		// Standalone setup
		new StandaloneSetup().setPlatformUri("../");
		Injector injector = new XcoreStandaloneSetup().createInjectorAndDoEMFRegistration();
		XtextResourceSet resourceSet = injector.getInstance(XtextResourceSet.class);
		resourceSet.addLoadOption(XtextResource.OPTION_RESOLVE_ALL, Boolean.TRUE);		
		resourceSet.getURIConverter().getURIMap().putAll(EcorePlugin.computePlatformURIMap(true));

		// Load Xcore
		URI metamodelUri = getFilename(args[0]);
		Resource metamodel = resourceSet.getResource(metamodelUri, true);
		metamodel.load(Collections.EMPTY_MAP);
		EcoreUtil.resolveAll(metamodel);

        XPackage xPackage = (XPackage)EcoreUtil.getObjectByType(metamodel.getContents(), XcorePackage.Literals.XPACKAGE);

		XcoreGenerator instance = injector.getInstance(XcoreGenerator.class);
		instance.generateBodyAnnotations(xPackage);
        
        System.out.println(xPackage);
	}

	public static URI getFilename(String filename) {
		if (filename == null)
			throw new IllegalArgumentException("Filename cannot be blank");
		return URI.createFileURI(filename);
	}

}
