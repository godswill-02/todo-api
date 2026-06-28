package com.todo.app.ports;

/**
 * Port de service pour le stockage de médias externes (S3, GCS, etc.).
 * Le service métier dépend uniquement de cette interface.
 */
public interface MediaStoragePort {

    /**
     * Upload un fichier binaire vers un stockage externe et retourne l'URL publique accessible.
     *
     * @param fileBytes Les octets du fichier média (Image, Vidéo, Audio).
     * @param mimeType Le type MIME du média (ex: image/jpeg).
     * @param contextIdentifier Un identifiant unique lié au todo pour le retrouver facilement (ID do).
     * @return L'URL publique de l'objet média stocké.
     * @throws MediaStorageException si l'upload échoue ou si les données sont invalides.
     */
    String storeMedia(byte[] fileBytes, String mimeType, Long contextIdentifier) throws MediaStorageException;

    /**
     * Exception spécifique pour signaler un échec de stockage média.
     */
    class MediaStorageException extends RuntimeException {
        public MediaStorageException(String message) {
            super(message);
        }
        public MediaStorageException(String message, Throwable cause) {
            super(message, cause);
        }
    }
}