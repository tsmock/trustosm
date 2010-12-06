package org.openstreetmap.josm.plugins.trustosm.gui;

import static org.openstreetmap.josm.tools.I18n.tr;

import java.io.IOException;

import org.openstreetmap.josm.gui.ExceptionDialogUtil;
import org.openstreetmap.josm.gui.PleaseWaitRunnable;
import org.openstreetmap.josm.io.OsmTransferException;
import org.xml.sax.SAXException;

public class KeyGenerationTask extends PleaseWaitRunnable {

    private boolean cancelled;
    private Exception lastException;

    public KeyGenerationTask() {
        super(tr("Generating new Keypair.\nCollecting randomness..."));
    }

    @Override
    protected void cancel() {
        cancelled = true;
        synchronized(this) {
            /*		if (objectReader != null) {
                objectReader.cancel();
            }*/
        }
    }

    @Override
    protected void finish() {
        if (cancelled)
            return;
        if (lastException != null) {
            ExceptionDialogUtil.explainException(lastException);
        }

    }

    @Override
    protected void realRun() throws SAXException, IOException,
    OsmTransferException {
        try {
            /*			synchronized (this) {
                if (cancelled) return;
                objectReader = new MultiFetchServerObjectReader();
            }
            objectReader.append(missing);
            progressMonitor.indeterminateSubTask(
                    buildDownloadFeedbackMessage()
            );
            final DataSet dataSet = objectReader.parseOsm(progressMonitor
                    .createSubTaskMonitor(ProgressMonitor.ALL_TICKS, false));
            if (dataSet == null)
                return;
            synchronized (this) {
                if (cancelled) return;
                objectReader = null;
            }

            SwingUtilities.invokeLater(
                    new Runnable() {
                        public void run() {
                            curLayer.mergeFrom(dataSet);
                            curLayer.onPostDownloadFromServer();
                            AutoScaleAction.zoomTo(dataSet.allPrimitives());
                        }
                    }
            );
             */
        } catch (Exception e) {
            if (cancelled) {
                System.out.println(tr("Warning: ignoring exception because task is cancelled. Exception: {0}", e
                        .toString()));
                return;
            }
            lastException = e;
        }

    }

}