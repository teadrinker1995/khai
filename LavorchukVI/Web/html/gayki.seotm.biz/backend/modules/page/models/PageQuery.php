<?php
/**
 * Created by PhpStorm.
 * User: max
 * Date: 10.02.15
 * Time: 11:31
 */
namespace backend\modules\page\models;
use creocoder\nestedsets\NestedSetsQueryBehavior;

class PageQuery extends \yii\db\ActiveQuery
{
    public function behaviors() {
        return [
            NestedSetsQueryBehavior::className(),
        ];
    }
}