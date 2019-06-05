package com.abapblog.adt.quickfix;

import java.util.ArrayList;



import java.util.List;

import org.eclipse.jface.text.contentassist.CompletionProposal;
import org.eclipse.jface.text.contentassist.ICompletionProposal;
import org.eclipse.jface.text.quickassist.IQuickAssistInvocationContext;
import org.eclipse.jface.text.quickassist.IQuickAssistProcessor;
import org.eclipse.jface.text.source.Annotation;
import org.eclipse.swt.graphics.Image;

public class RemoveAllComments implements IQuickAssistProcessor {
	AbapQuickFixRemoveCommentsCodeParser commentParser;

	@Override
	public boolean canAssist(IQuickAssistInvocationContext context) {
		commentParser = new AbapQuickFixRemoveCommentsCodeParser();
		String sourceCode = context.getSourceViewer().getDocument().get();
		return commentParser.haveComment(sourceCode, 0, sourceCode.length());
	}

	@Override
	public boolean canFix(Annotation arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public ICompletionProposal[] computeQuickAssistProposals(IQuickAssistInvocationContext context) {
		List<ICompletionProposal> proposals = new ArrayList<>();
		if (canAssist(context)) {
		   String sourceCode = context.getSourceViewer().getDocument().get();
			Image image = null;
			CompletionProposal cPropAllComments = new CompletionProposal(commentParser.parse(sourceCode), 0,
					sourceCode.length(), 0, image, "Remove all ABAP Comments", null,
					"Removes all ABAP Comments from the code. Please think twice before using it.");
			proposals.add(cPropAllComments);
			return proposals.toArray(new ICompletionProposal[1]);

		};
		return null;

	}

	@Override
	public String getErrorMessage() {
		// TODO Auto-generated method stub
		return null;
	}

}
