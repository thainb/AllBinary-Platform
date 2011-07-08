/*
* AllBinary Open License Version 1
* Copyright (c) 2011 AllBinary
* 
* By agreeing to this license you and any business entity you represent are
* legally bound to the AllBinary Open License Version 1 legal agreement.
* 
* You may obtain the AllBinary Open License Version 1 legal agreement from
* AllBinary or the root directory of AllBinary's AllBinary Platform repository.
* 
* Created By: Travis Berthelot
* 
*/
package views.admin.inventory;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;

import views.business.context.modules.storefront.HttpStoreComponentView;
import abcs.logic.basic.path.AbPathUtil;
import abcs.logic.communication.log.LogFactory;
import abcs.logic.communication.log.LogUtil;
import abcs.logic.visual.media.MediaData;
import allbinary.business.context.modules.storefront.StoreFrontFactory;
import allbinary.business.context.modules.storefront.StoreFrontInterface;
import allbinary.business.user.commerce.inventory.item.BasicItem;
import allbinary.business.user.commerce.inventory.item.BasicItemData;
import allbinary.business.user.commerce.inventory.item.BasicItemView;
import allbinary.business.user.commerce.inventory.item.ItemInterface;
import allbinary.business.user.commerce.inventory.item.download.DownloadableItem;
import allbinary.business.user.commerce.inventory.item.download.DownloadableItemView;
import allbinary.logic.communication.http.file.upload.HttpFileUploadUtil;
import allbinary.logic.communication.http.request.HttpRequestUtil;
import allbinary.logic.communication.http.request.MultipartRequestParams;
import allbinary.logic.communication.http.request.RequestMapInterface;
import allbinary.logic.visual.transform.info.TransformInfoInterface;
import java.util.Vector;

public class InventoryItemView extends HttpStoreComponentView
    implements RequestMapInterface
{
    protected final HttpServletRequest request;
    private String imageFileName;
    private MediaData mediaData;
    protected ItemInterface itemInterface;
    protected Vector downloadableItemVector;
    private HashMap requestHashMap;

    public InventoryItemView(
        TransformInfoInterface transformInfoInterface)
        throws Exception
    {
        super(transformInfoInterface);

        this.request = (HttpServletRequest) this.getPageContext().getRequest();
        this.getFormData();
    }

    //Hack TWB - Edit, New, Delete
    public InventoryItemView(
        TransformInfoInterface transformInfoInterface, String empty)
        throws Exception
    {
        super(transformInfoInterface);
        this.request = (HttpServletRequest) this.getPageContext().getRequest();
    }

    public static int TYPE_ID = 10;

    public int getTypeId()
    {
        return TYPE_ID;
    }

    private void getFormData() throws Exception
    {
        this.setRequestHashMap(new MultipartRequestParams(request).toHashMap());

        Object imageFileItemObject =
            this.getRequestHashMap().get(BasicItemData.IMAGE);

        if (HttpFileUploadUtil.getInstance().isValid(imageFileItemObject))
        {
            FileItem fileItem = (FileItem) imageFileItemObject;

            if (fileItem != null && fileItem.getSize() > 1)
            {
                this.imageFileName = HttpRequestUtil.getInstance().generateFileName(
                    fileItem.getName());

                this.mediaData = MediaData.get(AbPathUtil.getInstance().getExtension(this.imageFileName));
                this.imageFileName = AbPathUtil.getInstance().getWithoutExtension(this.imageFileName);

                if (abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.VIEWERROR))
                {
                    StringBuffer stringBuffer = new StringBuffer();

                    stringBuffer.append("Uploaded File Data: ");
                    stringBuffer.append(this.imageFileName);
                    stringBuffer.append(" Extension: ");
                    stringBuffer.append(this.mediaData.getName());

                    LogUtil.put(LogFactory.getInstance(stringBuffer.toString(), this, "getFormData()"));
                }
            }
        }
        this.itemInterface = (ItemInterface) new BasicItem(this.getRequestHashMap());
    }

    public void addDomNodeInterfaces()
    {
        Vector vector = new Vector();

        final int size = this.downloadableItemVector.size();
        for(int index = 0; index < size; index++)
        {
            DownloadableItem downloadableItem = (DownloadableItem)
                this.downloadableItemVector.get(index);

            vector.add(new DownloadableItemView(downloadableItem));
        }

        this.addDomNodeInterface(new BasicItemView(itemInterface, vector));
    }

    public ItemInterface getItemInterface()
    {
        return this.itemInterface;
    }

    public String view() throws Exception
    {
        try
        {
            this.addDomNodeInterfaces();
            return super.view();
        } catch (Exception e)
        {
            String error = "Failed to view";
            if (abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.VIEWERROR))
            {
                LogUtil.put(LogFactory.getInstance(error, this, "view()", e));
            }
            throw e;
        }
    }

    protected void processImageFiles() throws Exception
    {
        //Do not update image if file was not provided
        Set set = this.getRequestHashMap().keySet();
        Iterator iter = set.iterator();
        while (iter.hasNext())
        {
            String fieldName = (String) iter.next();
            if (fieldName.compareTo(BasicItemData.IMAGE) == 0)
            {
                StoreFrontInterface storeFrontInterface =
                    StoreFrontFactory.getInstance(
                    this.getWeblisketSession().getStoreName());

                InventoryUploadMediaUtil inventoryUploadMediaUtil =
                    new InventoryUploadMediaUtil(
                    storeFrontInterface, this.itemInterface);

                FileItem fileItem = (FileItem) this.getRequestHashMap().get(BasicItemData.IMAGE);

                this.itemInterface = inventoryUploadMediaUtil.saveFiles(
                    fileItem.get(), this.imageFileName, this.mediaData);
            }
            //if(!imageFile.isFile()) throw new Exception("Image File Did Not Save");
        }
    }

    private void setRequestHashMap(HashMap requestHashMap)
    {
        this.requestHashMap = requestHashMap;
    }

    public HashMap getRequestHashMap()
    {
        return requestHashMap;
    }
}
