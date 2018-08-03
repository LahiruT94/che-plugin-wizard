/*
 * Copyright (c) 2012-2018 Red Hat, Inc.
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 */
package org.eclipse.che.sample.ide.action;

import com.google.common.base.Optional;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import org.eclipse.che.ide.api.action.ActionEvent;
import org.eclipse.che.ide.api.action.ProjectAction;
import org.eclipse.che.ide.api.app.AppContext;
import org.eclipse.che.ide.api.resources.Project;
import org.eclipse.che.ide.api.selection.Selection;
import org.eclipse.che.ide.part.explorer.project.ProjectExplorerPresenter;
import org.eclipse.che.sample.ide.SampleWizardLocalizationConstant;
import org.eclipse.che.sample.ide.SampleWizardResources;
import org.eclipse.che.sample.ide.file.NewXFilePresenter;

/**
 * Action to create new X source file.
 *
 * @author Vitalii Parfonov
 */
@Singleton
public class NewXFileAction extends ProjectAction {
  private final AppContext appContext;
  private ProjectExplorerPresenter projectExplorer;
  private NewXFilePresenter newXFilePresenter;

  @Inject
  public NewXFileAction(
      ProjectExplorerPresenter projectExplorer,
      NewXFilePresenter newXFilePresenter,
      SampleWizardLocalizationConstant constant,
      SampleWizardResources resources,
      AppContext appContext) {
    super(constant.newXFile(), constant.createXFileWithIncludedHeader(), resources.xFile());
    this.newXFilePresenter = newXFilePresenter;
    this.projectExplorer = projectExplorer;
    this.appContext = appContext;
  }

  @Override
  public void actionPerformed(ActionEvent e) {

    newXFilePresenter.showDialog();
  }

  @Override
  public void updateProjectAction(ActionEvent e) {
    final Optional<Project> relatedProject = appContext.getResource().getRelatedProject();
    if (!relatedProject.isPresent()) {
      e.getPresentation().setEnabledAndVisible(false);
      return;
    }

    Selection<?> selection = projectExplorer.getSelection();
    if (selection == null) {
      e.getPresentation().setEnabledAndVisible(false);
      return;
    }

    e.getPresentation().setVisible(true);
    e.getPresentation().setEnabled(true);
  }
}
