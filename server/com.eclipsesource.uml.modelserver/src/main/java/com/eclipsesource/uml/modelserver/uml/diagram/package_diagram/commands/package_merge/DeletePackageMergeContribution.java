package com.eclipsesource.uml.modelserver.uml.diagram.package_diagram.commands.package_merge;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emfcloud.modelserver.command.CCommand;
import org.eclipse.emfcloud.modelserver.common.codecs.DecodingException;
import org.eclipse.emfcloud.modelserver.edit.command.BasicCommandContribution;
import org.eclipse.uml2.uml.PackageMerge;

import com.eclipsesource.uml.modelserver.core.commands.noop.NoopCommand;
import com.eclipsesource.uml.modelserver.shared.codec.ContributionDecoder;
import com.eclipsesource.uml.modelserver.shared.codec.ContributionEncoder;

public final class DeletePackageMergeContribution extends BasicCommandContribution<Command> {

   public static final String TYPE = "package:delete_package_merge";

   public static CCommand create(final PackageMerge semanticElement) {
      return new ContributionEncoder().type(TYPE).element(semanticElement).ccommand();
   }

   @Override
   protected Command toServer(final URI modelUri, final EditingDomain domain, final CCommand command)
      throws DecodingException {
      var decoder = new ContributionDecoder(modelUri, domain, command);

      var context = decoder.context();
      var element = decoder.element(PackageMerge.class);

      return element
         .<Command> map(e -> new DeletePackageMergeCompoundCommand(context, e))
         .orElse(new NoopCommand());
   }

}