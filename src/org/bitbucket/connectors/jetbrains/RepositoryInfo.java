package org.bitbucket.connectors.jetbrains;

import org.jdom.Element;

public class RepositoryInfo {
    private Element myRepositoryElement;

    RepositoryInfo(Element repositoryElement) {
        myRepositoryElement = repositoryElement;
    }

    public String getName() {
        return myRepositoryElement.getChildText("name");
    }

    public String getSlug() {
        return myRepositoryElement.getChildText("slug");
    }

    public String getOwner() {
        return myRepositoryElement.getChildText("owner");
    }

    public String isPrivate() {
        return myRepositoryElement.getChildText("is_private");
    }

    public String getCheckoutUrl() {
        String owner = getOwner();
        return getCheckoutUrl(BitbucketSettings.getInstance().getLogin().equals(owner));
    }

    public String getCheckoutUrl(boolean ssh) {
        String name = getSlug();
        String owner = getOwner();

        if (ssh) {
            return "ssh://hg@" + BitbucketUtil.BITBUCKET + "/" + owner + "/" + name;
        } else {
            return "https://" + BitbucketUtil.BITBUCKET + "/" + owner + "/" + name;
        }
    }
}
