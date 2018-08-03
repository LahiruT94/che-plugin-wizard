/*
 * Copyright (c) 2012-2018 Red Hat, Inc.
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 */
package org.eclipse.che.sample.ide.inject;

import static org.eclipse.che.sample.shared.Constants.C_EXT;

import com.google.gwt.inject.client.AbstractGinModule;
import com.google.gwt.inject.client.multibindings.GinMultibinder;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import com.google.inject.name.Named;
import org.eclipse.che.ide.api.extension.ExtensionGinModule;
import org.eclipse.che.ide.api.filetypes.FileType;
import org.eclipse.che.ide.api.project.type.wizard.ProjectWizardRegistrar;
import org.eclipse.che.sample.ide.SampleWizardResources;
import org.eclipse.che.sample.ide.file.NewXFileView;
import org.eclipse.che.sample.ide.file.NewXFileViewImpl;
import org.eclipse.che.sample.ide.wizard.SampleWizardRegistrar;

/** @author Vitalii Parfonov */
@ExtensionGinModule
public class SampleWizardGinModule extends AbstractGinModule {

  /** {@inheritDoc} */
  @Override
  protected void configure() {
    GinMultibinder.newSetBinder(binder(), ProjectWizardRegistrar.class)
        .addBinding()
        .to(SampleWizardRegistrar.class);
    bind(NewXFileView.class).to(NewXFileViewImpl.class).in(Singleton.class);
  }

  @Provides
  @Singleton
  @Named("XFileType")
  protected FileType provideXFile() {
    return new FileType(SampleWizardResources.INSTANCE.xFile(), C_EXT);
  }
}
